pip install isodate
pip install coverage
pip install pandas 

sudo apt update
sudo apt-get install graphviz -y


mkdir -p /home/codespace/.vscode-remote/extensions/ 

# ALFA IDE Setup
mkdir /tmp/alfa
curl -L --http1.1 http://alfa-lang.io/downloads/alfa-3.4.0.vsix --output /tmp/alfa/alfa-3.4.0.vsix > /dev/null
cd /tmp/alfa
unzip -d p1 alfa-3.4.0.vsix
mv p1/extension /home/codespace/.vscode-remote/extensions/schemarise.alfa-3.4.0

# ALFA Assistant Setup
curl -L --http1.1 http://alfa-lang.io/downloads/alfa-assistant-3.4.0.vsix --output /tmp/alfa/alfa-assistant-3.4.0.vsix > /dev/null 
cd /tmp/alfa
unzip -d p2 alfa-assistant-3.4.0.vsix
mv p2/extension /home/codespace/.vscode-remote/extensions/schemarise.alfa-assistant-3.4.0

# ALFA CLI Setup
mkdir -p /home/codespace/.alfa
curl -L --http1.1 http://alfa-lang.io/downloads/AlfaPackage-3.4.0.zip --output /home/codespace/.alfa/AlfaPackage-3.4.0.zip > /dev/null 
cd /home/codespace/.alfa 
unzip AlfaPackage-3.4.0.zip
rm -rf samples

# ALFA Python Setup
curl -L --http1.1 http://alfa-lang.io/downloads/alfa-pylib-3.4.0.tar.gz --output /tmp/alfa-pylib-3.4.0.tar.gz > /dev/null 
pip install /tmp/alfa-pylib-3.4.0.tar.gz
