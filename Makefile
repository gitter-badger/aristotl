.PHONY: virtualenv


#if [ $(python3) ] then
#	WHICH = $(which python3)
#elif [ $(python) ] then
#	WHICH = $(which python)
#fi


VENV = venv
PYTEST_OPTIONS = -vv --exitfirst -n 8
export PYTHONPATH := $(PYTHONPATH):.

# Run and serve the client Python app
client: $(VENV)
	. $(VENV)/bin/activate

# Run the python shell
shell:
	python shell.py

test: $(VENV)
	. $(VENV)/bin/activate; py.test $(PYTEST_OPTIONS) tests/

env:
	virtualenv -p $(WHICH) $(VENV)
