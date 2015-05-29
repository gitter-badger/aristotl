


build:
	cabal build

nix:
	cabal2nix --shell ./ > shell.nix

# Only needs to be run after changing aristotl.cabal
configure: shell.nix
	nix-shell -I ./ --command 'cabal configure'
