.PHONY: virtualenv


VENV = env
PYTEST_OPTIONS = -vv --exitfirst -n 8
export PYTHONPATH := $(PYTHONPATH):.

# Run and serve the client Python app
client: $VENV
	source ./$VENV/bin/activate

# Run the python shell
shell:
	python shell.py

test: $VENV
	. $VENV/bin/activate; py.test $(PYTEST_OPTIONS) tests/

env:
	virtualenv $VENV

install:
	pip install -r requirements.txt
