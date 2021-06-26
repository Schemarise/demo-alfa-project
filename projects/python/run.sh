# Set required env vars
eval $(gp env -e)

# Generate Python code for the model
$ALFA_HOME/bin/alfa -o generated -e python -c ../../model/

export PYTHONPATH=$PYTHONPATH:$PWD/generated

# Run pandas sample
python pandas/pan-sample.py

# Run the sample
python basic/sample.py
