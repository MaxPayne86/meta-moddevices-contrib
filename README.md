Meta-Moddevices-Contrib
================================

Moddevices Linux OpenEmbedded/Yocto Project base layer community contributed.

Dependencies:
- see conf/layer.conf

Usage:
- there is an example self-contained docker container image built with yocto

```
bitbake moddevices-container
```

you can find example local.conf and bblayers.conf under conf dir

NOTES for developers:
- aims of this layer is to provide equivalent to mod-live-usb v0.8 and subsequent versions
- dunfell branch is active
- rocko branch is abandoned (reference only)
- serd, sord, sratom, lilv fails do_configure, you can solve manually by invoking

```
find . -name "Node.py" -exec sed -i "s/raise StopIteration/return/g" {} +
```

in the workdir/git of the package