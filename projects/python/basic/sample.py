from schemarise.alfa.runtime.JsonCodec import JsonCodec
from schemarise.alfa.runtime.AlfaRandomizer import AlfaRandomizer
from schemarise.alfa.runtime.BuilderConfig import BuilderConfig

import datetime
import uuid

from demo.model.CustomerType import CustomerType
from demo.model.Customer import Customer
from demo.model.CustomerKey import CustomerKey

from schemarise.alfa.runtime.DFBuilder import DFBuilder

# Create an Alfa Randomiser class
randomizer = AlfaRandomizer([], BuilderConfig( should_validate_onbuild = False ))

# Create a random Customer object 
rand_obj = randomizer.random("demo.model.Customer")

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
cb.Accounts = [ "11231244", "32421232", "22903480" ]
cb.CustType = CustomerType.Standard
cb.PassCodeToken = "Secr3t1!"

c = cb.build()
print("------ Object to JSON ------")
print( JsonCodec.to_json(c) )

print("------ Object to Python Pandas DataFrame ------")
df = DFBuilder.from_alfa_object(c)
print(df)
