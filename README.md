<span id="top"></span>
<h1 align="center">
  <img src="https://icon-library.com/images/counter-strike-icon/counter-strike-icon-29.jpg" width="128" />
  <br> MixWeb | Gerenciador para partidas de mix no CS
</h1>

<p align="center">
<!-- Version -->
  <a href="https://github.com/heliomarpm/udemy-downloader-gui/releases" target="_blank" rel="noopener noreferrer">
     <img alt="releases url" src="https://img.shields.io/github/v/release/johnattamedeiros/mixweb?style=for-the-badge&labelColor=1C1E26&color=2ea043"/>
  </a>
  <!-- Downloads -->
  <a href="https://github.com/heliomarpm/udemy-downloader-gui/releases" target="_blank" rel="noopener noreferrer">
    <img alt="GitHub Downloads" src="https://img.shields.io/github/downloads/johnattamedeiros/mixweb/total?style=for-the-badge&labelColor=1C1E26&color=2ea043">
  </a>
  <!-- Issues -->
  <!-- <a href="https://github.com/heliomarpm/udemy-downloader-gui/issues?q=is%3Aissue+is%3Aopen+sort%3Aupdated-desc" target="_blank">
    <img alt="GitHub issues" src="https://img.shields.io/github/issues/johnattamedeiros/mixweb?style=for-the-badge&labelColor=1C1E26">
  </a> -->
</p>

<div align="center">

  Este sistema permite que através das demos do CS, seja possível recolher as estatísticas do jogo e montar um ranking com estes dados, fazer links com perfil da STEAM e ter top ranking específicos<br>
  
 
</div>

## To Use

To clone and run this repository you'll need [Git](https://git-scm.com) and [Java 11](https://adoptium.net/temurin/releases?version=11) \
From your command line:

```bash
# Clone this repository
git clone https://github.com/johnattamedeiros/mixweb.git
# Go into the repository
cd mixweb

# Setup database
You need create a database to setup the project, builded on Postgres


Postgres configs:
Path config by docker: mixweb/src/main/docker/postgresql.yml

    environment:
      - POSTGRES_USER=mixweb
      - POSTGRES_PASSWORD=
    ports:
      - 5432:5432

# Run the app
Run by maven or setup on docker



