FROM gitpod/workspace-full

RUN pip install isodate
RUN pip install coverage
RUN pip install pandas 

ENV GRAPHVIZ_DOT=/usr/bin/dot
ENV ALFA_TOOLS_PORT=8000
