# Sistema de Redirecionamento de URLs Encurtadas

<p>O processo de redirecionamento de URL encurtada na AWS é composto pelos seguintes passos: o API Gateway recebe a requisição contendo a URL encurtada e aciona uma função Lambda. Na função Lambda, é realizado o processo de deserialização do código da URL para recuperar o URL original armazenado no banco de dados. Após essa recuperação, a Lambda retorna uma resposta com um redirecionamento HTTP (código 301 ou 302), direcionando o usuário para a URL original.</p>

# Tecnologias utilizadas

* <img src="https://img.shields.io/badge/-AWS%20Lambda-FF9900?style=flat&logo=awslambda&logoColor=white" alt="AWS Lambda">
* <img src="https://img.shields.io/badge/-Amazon%20S3-232F3E?style=flat&logo=amazons3&logoColor=569A31" alt="Amazon S3">
* <img src="https://img.shields.io/badge/-API%20Gateway-232F3E?style=flat&logo=amazonapigateway&logoColor=purple" alt="API Gateway">
* <img src="https://img.shields.io/badge/-JSON-232F3E?style=flat&logo=json&logoColor=#000000" alt="JSON">
* <img src="https://img.shields.io/badge/-Maven-232F3E?style=flat&logo=apachemaven&logoColor=C71A36" alt="Maven">



