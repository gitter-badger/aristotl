


build:
	cabal build

nix:
	cabal2nix aristotl.cabal --sha256=0 --shell > shell.nix

# Only needs to be run after changing aristotl.cabal
configure: shell.nix
	nix-shell --command 'cabal configure'
