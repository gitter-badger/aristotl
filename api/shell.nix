with (import <nixpkgs> {}).pkgs;
let pkg = haskellngPackages.callPackage
            ({ mkDerivation, base, http, hxt, stdenv, url }:
             mkDerivation {
               pname = "aristotl";
               version = "0.1.0.0";
               src = ./.;
               isLibrary = false;
               isExecutable = true;
               buildDepends = [ base http hxt url ];
               homepage = "http://aristotl.co";
               description = "The Philosophy API";
               license = stdenv.lib.licenses.mit;
             }) {};
in
  pkg.env
