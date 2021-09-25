FROM gitpod/workspace-full

RUN pip install isodate
RUN pip install coverage
RUN pip install pandas 

ENV GRAPHVIZ_DOT=/usr/bin/dot
ENV ALFA_TOOLS_PORT=8000

RUN echo "export PATH=\"\$PATH:$ALFA_HOME/bin\"" >> /home/gitpod/.bashrc
