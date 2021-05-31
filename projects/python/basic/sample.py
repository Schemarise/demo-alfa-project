from alfa.rt.JsonCodec import JsonCodec
from alfa.rt.TableCodec import TableCodec
from alfa.rt.AlfaRandomizer import AlfaRandomizer
from alfa.rt.BuilderConfig import BuilderConfig
from alfa.rt.DFBuilder import DFBuilder
import pandas as pd
import sys

# ----------------------------------------------------------------------------------------------------------------
# Create an Alfa Randomiser class
randomizer = AlfaRandomizer([])
# Create a random Position object 
rand_obj = randomizer.random("acme.model.Customer")

# Convert Generated Alfa object to JSON and print it
json = JsonCodec.to_json(rand_obj)
print(json)
