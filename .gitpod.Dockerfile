FROM gitpod/workspace-full

RUN pip install isodate
RUN pip install coverage
RUN pip install pandas 

ENV GRAPHVIZ_DOT=/usr/bin/dot
ENV ALFA_TOOLS_PORT=8000

RUN echo "export ALFA_HOME=$HOME/.alfa" >> /home/gitpod/.bash_aliases
RUN echo "export PATH=$PATH:$HOME/.alfa/bin" >> /home/gitpod/.bash_aliases
