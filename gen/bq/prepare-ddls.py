

# pip install google-cloud-bigquery required

from google.cloud import bigquery
import google.api_core.exceptions as core_exceptions

import json
import sys
import os
from pathlib import Path
import time
import io


def main():
    if len(sys.argv) != 4:
        raise Exception("Expected 3 arguments <alfa-models directory> <project-name> <dataset-name>")

    files = []
    for f in os.listdir(sys.argv[1]):
        if f.startswith("bq-table-") and f.endswith(".json"):
            files.append(os.path.join(sys.argv[1], f))

    if os.path.exists('generated'):
        timestr = time.strftime("%Y%m%d-%H%M%S")
        os.rename('generated', 'generated-pre-' + timestr)

    Path('generated').mkdir(exist_ok=True)

    client = bigquery.Client()
    for t in files:
        with open(t, 'r') as spec:
            data=spec.read()

        project = sys.argv[2]
        dataset = sys.argv[3]
        model = json.loads(data)

        tableName = model['tableName']
        tbl = project + "." + dataset + "._" + tableName
        viw = project + "." + dataset + "." + tableName

        print(f"- Processing {tableName}")
        recreate_view = create_or_update_table(client, tbl, model)
        if recreate_view:
            create_view(viw, tbl, model)


def create_view(view, tbl, model):
    col_names = []
    for s in model.get('fields'):
        col_names.append(s['name'])

    cols = ", ".join(col_names)
    sql = f"CREATE OR REPLACE VIEW {view} AS SELECT {cols} FROM {tbl}"
    vw = f"create-view-{view}.sql"
    write(vw, sql)

def fields_as_map( f ):
    res = {}
    for e in f:
        res[e['name']] = e
        if e.get('fields') is not None:
            e['fields'] = fields_as_map(e['fields'])
    return res

def update_table_schema(client, table_id, tabledef, model):
    schema = tabledef.schema
    f = io.StringIO("")
    client.schema_to_json(schema,f)
    current_fields_model = json.loads(f.getvalue())

    new_model_fields_map = fields_as_map(model['fields'])
    bq_model_fields_map = fields_as_map(current_fields_model)

    fields_matches = bq_model_fields_map == new_model_fields_map

    if fields_matches:
        print(f"Table {table_id} schema has not changed")
        return False
    else:
        print(f"Table {table_id} schema has changed")
        updated_schema = json.dumps(model['fields'], indent=4)
        schema_file = f"schema-{tabledef.full_table_id}.json"
        write(schema_file, updated_schema)
        return True


def create_or_update_table(client, table_id, model):
    try:
        table = client.get_table(table_id)
        return update_table_schema(client, table_id, table, model)
    except core_exceptions.NotFound:
        create_table(table_id, model)
        return True


def sql_columns(fields, term = ""):
    converted_list = [sql_column(e) for e in fields]
    return (", " + term).join(converted_list)


def sql_column(f):
    name = f.get('name')
    type = f.get('type')
    mode = f.get('mode')
    desc = f.get('description')
    flds = f.get('fields')

    t = type

    if type=="RECORD":
        s = sql_columns(flds)
        t = f"STRUCT< {s} >"

    if mode=="REPEATED":
        t = f"ARRAY< {t} >"

    if mode=="REQUIRED":
        t += " NOT NULL"

    if desc is not None:
        t += f" OPTIONS( description='{desc}' )"

    return f"{name} {t}"

def write(fname, contents):
    f = open( os.path.join("generated", fname) , "w")
    f.write(contents)
    f.close()

def create_table(table_id, model):
    cols = sql_columns(model['fields'], "\n  ")
    sql = f"CREATE TABLE {table_id} (\n" +\
          f"  {cols}\n" +\
          ")\n"

    if model.get('partitionFields') is not None:
        sql += "PARTITION BY " + (", ").join(model.get('partitionFields')) + "\n"

    if model.get('partitionExpression') is not None:
        sql += "PARTITION BY " + model.get('partitionExpression') + "\n"

    if model.get('clusterFields') is not None:
        sql += "CLUSTER BY " + (", ").join(model.get('clusterFields')) + "\n"

    all_opts = []
    if model.get('options') is not None:
        opts = model.get('options')
        all_opts.append( ', '.join( e[1:-1] for e in opts) )

    if model.get('labels') is not None:
        labels = model.get('labels')
        all_opts.append( 'labels=[' + ', '.join(f"'{k}'='{v}'" for k, v in labels) + ']' )

    if len(all_opts) > 0:
        sql += f"OPTIONS ({ ', '.join(all_opts) })\n"

    fname = f"create-table-{table_id}.sql"
    write( fname, sql)

main()





