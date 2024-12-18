# Set required env vars
if type gp > /dev/null 2>&1 
then
    eval $(gp env -e)
fi

# Generate Python code for the model
$ALFA_HOME/bin/alfa -o generated -e python -c ../../model/

export PYTHONPATH=$PYTHONPATH:$PWD/generated

# Run pandas sample
python pandas/pan-sample.py

# Run the sample
python basic/sample.py
