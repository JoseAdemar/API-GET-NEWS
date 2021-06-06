# API-GET-NEWS
API RESTFUL

## Explicação do Projeto
Realizei a criação de uma API RESTFUL para capturar informações de página HTML,
usando a biblioteca Java Jsoup. Através dessa biblioteca peguei informações
especificas como:<br/>
A URL da notícia;<br/>
O título da notícia;<br/>
O subtítulo da notícia;<br/>
Autor;<br/>
A data de publicação no formato (dia/mês/ano hora:minuto) conforme está na página HTML;<br/>
O conteúdo da notícia, sem tags html e sem quebras de linha<br/>
## O que funciona na API ?
Captura sempre as 3 primeiras noticia do site especificado no method.<br/>
Pegando o conteúdo de cada noticia capturada.
Criei um CRUD com as informações capturadas acima.<br/>
Criei endpoint para capturar as informações e salva-las no banco de dados<br/>
Criei endpoint para alterar as informações que foram salvas no banco de dados.<br/>
Criei endpoint para pesquisar as informações salva no banco de dados.<br/>
Criei endpoint para deletar informações que foram salvas no banco de dados.<br/>
Também realizei tratamento de Exceptions, dando um feedback do erro ocorrido caso aconteça alguma Exception.



