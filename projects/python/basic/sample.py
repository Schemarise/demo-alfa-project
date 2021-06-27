from alfa.rt.JsonCodec import JsonCodec
from alfa.rt.AlfaRandomizer import AlfaRandomizer

import datetime
import uuid

from acme.model.CustomerType import CustomerType
from acme.model.Customer import Customer
from acme.model.CustomerKey import CustomerKey

from alfa.rt.DFBuilder import DFBuilder

# Create an Alfa Randomiser class
randomizer = AlfaRandomizer([])

# Create a random Customer object 
rand_obj = randomizer.random("acme.model.Customer")

# Convert Generated Alfa object to JSON and print it
json = JsonCodec.to_json(rand_obj)
print(json)


# Create a Customer object and print it

ckb = CustomerKey.new_builder()
ckb.cusId = uuid.uuid4()
ck = ckb.build()

cb = Customer.new_builder()
cb.__key__ = ck
cb.FirstName = "Joe"
cb.LastName = "Bloggs"
cb.DateOfBirth = datetime.date(2000, 5, 17)
cb.Accounts = [ "1123124", "3241232", "2903480" ]
cb.CustType = CustomerType.Standard

c = cb.build()
print("------ Object to JSON ------")
print( JsonCodec.to_json(c) )

print("------ Object to Python Pandas DataFrame ------")
df = DFBuilder.from_alfa_object(c)
print(df)
