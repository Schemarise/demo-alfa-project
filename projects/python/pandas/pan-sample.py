from schemarise.alfa.runtime.JsonCodec import JsonCodec
from schemarise.alfa.runtime.AlfaRandomizer import AlfaRandomizer
from schemarise.alfa.runtime.BuilderConfig import BuilderConfig

from schemarise.alfa.runtime.DFBuilder import DFBuilder
import pandas as pd

from demo.model.CustomerType import CustomerType
from demo.model.Customer import Customer
from demo.model.CustomerKey import CustomerKey

# Create an Alfa Randomiser class
randomizer = AlfaRandomizer([], BuilderConfig( should_validate_onbuild = False ) )

# Create 5 random Customer objects 
customers = []
for i in range(6):
    customers.append( randomizer.random("demo.model.Customer") )

# Convert Generated Alfa objects to a Pandas dataframes
df = DFBuilder.from_alfa_objects(customers)
print(df)

gold_cust = df[df.CustType == 'Gold']
print("--- FILTERED ---")
print(gold_cust)

