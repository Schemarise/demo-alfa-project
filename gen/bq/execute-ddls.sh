
shopt -s nullglob

cd generated

echo ------------- Creating tables -------------

for i in create-table-*.sql; do
    echo "- Creating table" $i
    cat $i | bq query --use_legacy_sql=false
    if [ $? -ne 0 ]; then
        exit -1
    fi
done

echo ------------- Updating tables -------------

for i in schema-*.json; do
    full_table_id=`basename "$i" .json | cut -c 8-`
    echo "- Updating table" $full_table_id
    bq update $full_table_id $i
    if [ $? -ne 0 ]; then
        exit -1
    fi
done

echo ------------- Refreshing Views -------------

for i in create-view-*.sql; do
    echo "- Refreshing view" $i
    cat $i | bq query --use_legacy_sql=false
    if [ $? -ne 0 ]; then
        exit -1
    fi
done

cd ..

echo Completed
      
