# Cadastro de Usuário

Bem-vindo ao Projeto de Cadastro de Usuário! Este é um projeto que requer algumas configurações específicas para funcionar corretamente. Siga as instruções abaixo para adicionar suas credenciais do AWS e PostgreSQL no arquivo .properties.

## Configuração do AWS

1. Crie uma conta no AWS em [https://aws.amazon.com](https://aws.amazon.com) se você ainda não tiver uma.

2. Acesse o painel de controle do AWS e crie as credenciais necessárias para acessar os serviços utilizados pelo projeto.

3. Abra o arquivo `.properties` na raiz do projeto.

4. Procure pela seção de configuração do AWS e adicione suas credenciais específicas, como mostrado abaixo:
  amazon.user.accesskey=sua_access_key_id
  amazon.user.secretkey=sua_secret_access_key
  amazon.user.bucket=seu_bucket
  amazon.user.location=local_do_bucket

  Certifique-se de substituir `sua_access_key_id`, `sua_secret_access_key` e `seu_bucket` e `local_do_bucket` pelas suas próprias informações fornecidas pelo AWS.

## Configuração do PostgreSQL

1. Instale e configure o PostgreSQL em seu ambiente local ou em um servidor remoto. Você pode encontrar instruções de instalação e configuração adequadas para o seu sistema operacional em [https://www.postgresql.org](https://www.postgresql.org).
  
2. Crie um banco de dados no PostgreSQL para o projeto.
  
3. Abra o arquivo `.properties` na raiz do projeto.
  
4. Procure pela seção de configuração do PostgreSQL e adicione suas credenciais específicas, como mostrado abaixo:
  spring.datasource.url=sua_base
  spring.datasource.username=seu_usuario
  spring.datasource.password=sua_senha

  Certifique-se de substituir `seu_host`, `sua_porta`, `seu_banco_de_dados`, `seu_usuario` e `sua_senha` pelas suas próprias informações de conexão com o PostgreSQL.

