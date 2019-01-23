# string-similarity
Implementations for various string similarity metrics.

[![DOI](https://zenodo.org/badge/98212408.svg)](https://zenodo.org/badge/latestdoi/98212408)

## Usage

If you want to use the `string-similarity` library in your project, you first need to configure the *SOTorrent* Maven repository, which is hosted on GitHub. Simply add this to your `pom.xml`:

    <repositories>
        <repository>
            <id>sotorrent</id>
            <url>https://raw.github.com/sotorrent/releases/master/</url>
            <snapshots>
                <enabled>true</enabled>
                <updatePolicy>always</updatePolicy>
            </snapshots>
        </repository>
    </repositories>

You can then add `string-similarity` as a dependency:

    <dependencies>
        <dependency>
            <groupId>org.sotorrent</groupId>
            <artifactId>string-similarity</artifactId>
            <version>LATEST</version>
        </dependency>
    </dependencies>
