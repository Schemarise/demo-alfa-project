from alfa.rt.JsonCodec import JsonCodec
from alfa.rt.AlfaRandomizer import AlfaRandomizer

from alfa.rt.TableCodec import TableCodec
from alfa.rt.BuilderConfig import BuilderConfig
from alfa.rt.DFBuilder import DFBuilder
import pandas as pd

from acme.model.CustomerType import CustomerType
from acme.model.Customer import Customer
from acme.model.CustomerKey import CustomerKey

# Create an Alfa Randomiser class
randomizer = AlfaRandomizer([])

# Create 5 random Customer objects 
customers = []
for i in range(5):
    customers.append( randomizer.random("acme.model.Customer") )

# Convert Generated Alfa objects to a Pandas dataframes
df = DFBuilder.from_alfa_objects(customers)
print(df)

gold_cust = df[df.CustType == 'Gold']
print(gold_cust)

