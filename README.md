# Bitcoin News Scraper

Projeto Java usando Selenium para coletar manchetes de notícias sobre Bitcoin, armazenar em um banco H2 em memória, e exibir resumos. Testado com TDD/JUnit5. Rodável via Docker.

## Como rodar com Docker

```
git clone https://github.com/andre5-augusto/bitcoin-news-scraper.git
cd bitcoin-news-scraper
docker-compose up --build
```

> O Selenium roda em modo "headless" via Grid Chrome standalone.
> O app Java consulta esse Selenium remoto (`http://selenium-chrome:4444/wd/hub`).

## Testes

Execute os testes com Maven:

```
./mvnw test
```

## Estrutura do Projeto

- `src/main/java/` Código principal
- `src/test/java/` Testes TDD com JUnit 5
- `Dockerfile` Build app em container Java
- `docker-compose.yml` Orquestra app + Selenium Chrome headless
- `README.md` Esta documentação

## Variáveis

Configure o endereço do Selenium pelo env `SELENIUM_REMOTE_URL` se mudar o host do Selenium Grid.

## Comandos úteis

- Para rodar só o app (usando Selenium local):  
  `docker build -t bitcoin-news-scraper . && docker run bitcoin-news-scraper`
- Para rodar com comunicação entre containers (recomendado para scraping real):  
  `docker-compose up --build`

## Dicas

- Adicione outros sites na classe do Scraper para ampliar fontes de notícias.
- O banco é volátil (memória). Para persistência, troque por H2 file ou outro banco.
