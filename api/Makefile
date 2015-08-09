


build:
	cabal build

nix:
	cabal2nix --shell ./ > default.nix

# Only needs to be run after changing aristotl.cabal
configure: default.nix
	nix-shell -I ./ --command 'cabal configure'
