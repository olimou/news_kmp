
# 📰 Leitor de Notícias KMP
O Leitor de Notícias KMP é um aplicativo multiplataforma desenvolvido com Kotlin Multiplatform que  
permite aos usuários ler as últimas notícias de diversas fontes. Utilizando a API da NewsAPI.org, o  
aplicativo exibe uma lista de notícias e, ao selecionar uma notícia, abre o link da matéria original  
em um navegador.

## 🚀 Tecnologias e Bibliotecas Utilizadas
- Jetpack Compose: Utilizado para construir a interface do usuário de forma declarativa no Android.
- Koin: Framework de injeção de dependência escolhido para gerenciar as dependências do projeto.
- Ktor: Cliente HTTP usado para fazer chamadas de rede e consumir a API da NewsAPI.org.
- Coil: Biblioteca de carregamento de imagens para carregar e exibir imagens de forma eficiente.

## 🌍 Plataformas Suportadas
-   **Android**: ✅ Disponível.
-   **iOS**: 🚧 Em breve.
-   **Web**: 🚧 Em breve.

## 🔍 Qualidade de Código
- Jacoco: 🚧: Ferramenta de cobertura de código utilizada para monitorar a qualidade do código.  
  Atualmente, a integração está em andamento.

## 🏗 Estrutura do Projeto
O projeto é modularizado por funcionalidades para promover o desacoplamento e a reusabilidade do  
código. Abaixo estão os módulos principais:

-   **androidApp**: Módulo do aplicativo principal contendo a UI específica do Android e a lógica de inicialização.
-   **articles**: Módulo contendo a lógica e as interfaces de usuário para a listagem de notícias.
-   **Core**:
    -   **ext**: Módulo que inclui extensões Kotlin úteis usadas em todo o projeto.
    -   **Network**: Módulo com a camada de rede, utilizando Ktor para as chamadas de API.
-   **share**: Módulo com informações e funcionalidades compartilhadas entre as plataformas suportadas.

## 📦 Gerenciamento de Dependências
Para organizar e gerenciar as dependências do projeto, utilizamos o arquivo libs.version.toml. Este  
formato TOML (Tom's Obvious, Minimal Language) permite definir as versões das bibliotecas de forma  
centralizada e declarativa, facilitando a manutenção e atualização das dependências.

## 🔑 Configuração da API Key
Para acessar a API da NewsAPI.org, é necessário configurar uma chave de API. Você pode fazer isso de  
duas maneiras:

1.  **Variável de Ambiente**: Defina uma variável de ambiente chamada `NEWSAPI_API_KEY` com sua chave de API.
2.  **Alteração Manual**: Você também pode inserir sua chave de API diretamente no arquivo `androidApp/build.gradle.kts`, substituindo o valor `YOUR_API_KEY_HERE` pela sua chave de API real.