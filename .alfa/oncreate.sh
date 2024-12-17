sdk default java 17.0.13-ms

pip install isodate
pip install coverage
pip install pandas 

sudo apt update
sudo apt-get install graphviz -y


mkdir -p $HOME/.vscode-remote/extensions/ 

# ALFA IDE Setup
curl -L --http1.1 http://alfa-lang.io/downloads/alfa-3.4.0.vsix --output /tmp/alfa-3.4.0.vsix > /dev/null
cd /tmp
unzip -d p1 alfa-3.4.0.vsix
mv p1/extension $HOME/.vscode-remote/extensions/schemarise.alfa-3.4.0

# ALFA Assistant Setup
curl -L --http1.1 http://alfa-lang.io/downloads/alfa-assistant-3.4.0.vsix --output /tmp/alfa-assistant-3.4.0.vsix > /dev/null 
cd /tmp
unzip -d p2 alfa-assistant-3.4.0.vsix
mv p2/extension $HOME/.vscode-remote/extensions/schemarise.alfa-assistant-3.4.0

# ALFA CLI Setup
mkdir -p $HOME/.alfa
curl -L --http1.1 http://alfa-lang.io/downloads/AlfaPackage-3.4.0.zip --output $HOME/.alfa/AlfaPackage-3.4.0.zip > /dev/null 
cd $HOME/.alfa 
unzip AlfaPackage-3.4.0.zip
rm -rf samples

# ALFA Python Setup
curl -L --http1.1 http://alfa-lang.io/downloads/alfa-pylib-3.4.0.tar.gz --output /tmp/alfa-pylib-3.4.0.tar.gz > /dev/null 
pip install /tmp/alfa-pylib-3.4.0.tar.gz
