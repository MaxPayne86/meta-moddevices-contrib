Meta-Moddevices-Contrib
================================

Moddevices Linux OpenEmbedded/Yocto Project base layer community contributed.

#### Dependencies

- see conf/layer.conf

#### Usage

- there is an example self-contained docker container image built with yocto

```
bitbake moddevices-container-ampsim
```

you can find example local.conf and bblayers.conf under conf dir

#### Importing container image

```
docker import moddevices-container-ampsim-qemuarm64.tar.bz2 moddevices-container
```

#### Running container image

Warning: jackd in container is problematic and non officially supported at the moment, use big buffer sizes

```
docker run -it --rm -u 0 --cap-add=sys_nice --ulimit rtprio=95 --ulimit memlock=-1 --shm-size=128m --device=/dev/snd:/dev/snd --device=/dev/ttymxc3:/dev/ttymxc3 --env CARD=i2saudio --env SR=48000 --env BUFFER=1024 --env TTY=ttymxc3 moddevices-container sh
```

#### Developers

- aims of this layer is to provide equivalent to mod-live-usb v0.8 and subsequent versions
- dunfell branch is active
- rocko branch is abandoned (reference only)
- kirkstone is next
- serd, sord, sratom, lilv fails do_configure, you can solve manually by invoking

```
find . -name "Node.py" -exec sed -i "s/raise StopIteration/return/g" {} +
```

in the workdir/git of the package. The reason is an obsolete waf library referenced by the packages
and using old python syntax no more in use.
