# Sistema de Encurtamento de URLs: Gerenciamento e Redirecionamento na AWS
<p>
  O sistema de encurtamento de URLs funciona como um serviço que recebe uma URL longa, gera uma versão curta e armazena as informações de mapeamento entre a URL original e a URL curta. Quando um usuário acessa a URL curta, o sistema realiza um redirecionamento para a URL original. 
  O processo descrito no seu exemplo envolve a utilização de AWS Lambda, API Gateway e Amazon S3, além de outras tecnologias AWS para suportar o sistema de encurtamento e redirecionamento.
</p>

# 
![01](https://github.com/user-attachments/assets/402d832f-d971-47de-b91c-e237b4af5a72)


# Tecnologias Utilizadas
<div display="flex">
   <img src="https://img.shields.io/badge/-AWS%20Lambda-FF9900?style=flat&logo=awslambda&logoColor=white" alt="AWS Lambda">
   <img src="https://img.shields.io/badge/-Amazon%20S3-232F3E?style=flat&logo=amazons3&logoColor=569A31" alt="Amazon S3">
  <img src="https://img.shields.io/badge/-UUID-232F3E?style=flat&logo=UUID&logoColor=569A31" alt="UUID">

</div>

* <p> AWS Lambda: Serviço de computação sem servidor da AWS que executa o código em resposta a eventos, como uma requisição do API Gateway. Não há necessidade de provisionamento de servidores.</p>
* <p> Amazon S3 (Simple Storage Service): Usado para armazenar os dados das URLs e seus metadados, como o tempo de expiração. O S3 oferece alta disponibilidade e escalabilidade, permitindo armazenar e acessar rapidamente grandes volumes de dados.</p>
* <p> UUID (Universally Unique Identifier): Usado para gerar um identificador único para cada URL encurtada. Esse identificador é então convertido em uma URL curta, que pode ser compartilhada com os usuários.</p>
