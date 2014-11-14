.PHONY: virtualenv

ENV = env
PYTEST_OPTIONS = -vv --exitfirst -n 8
export PYTHONPATH := $(PYTHONPATH):.

# Run and serve the client Python app
client: $(ENV)
	. $(ENV)/bin/activate

# Run the python shell
shell:
	python shell.py

test: $(ENV)
	. $(ENV)/bin/activate; py.test $(PYTEST_OPTIONS) tests/
