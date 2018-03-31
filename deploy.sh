#!/bin/bash -

# TODO: check for TAG
if [ -z "${TAG}" ]; then
    echo "TAG must be set"
    exit 1
fi

git checkout $(git rev-parse HEAD)
mvn versions:set "-DnewVersion=${TAG}"
sed "s/0.0.0-SNAPSHOT/${TAG}/g" ./README.md > /tmp/README.md
mv /tmp/README.md ./README.md
mvn clean deploy -Possrh
git commit -am "[release] reqresp-${TAG}"
git tag ${TAG}
git push origin ${TAG}

