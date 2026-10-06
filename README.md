# 🎰 Loto Easy

> Aplicativo mobile para averiguação de resultados da **Lotomania**, desenvolvido como projeto acadêmico da disciplina de **Desenvolvimento Mobile** do IFPE.

---

## 📋 Sobre o Projeto

O **Loto Easy** é um aplicativo Android voltado para jogadores da **Lotomania**, um dos jogos de loteria mais populares da **Caixa Econômica Federal**. A Lotomania é um jogo em que o apostador marca de 0 a 50 números em um volante de 100 dezenas, e o sorteio seleciona 20 dezenas vencedoras — quem acertar mais dezenas (ou até mesmo zero) pode ganhar prêmios.

O aplicativo permite que o usuário **cadastre seus jogos e confira automaticamente se foi premiado**, consultando os resultados oficiais dos sorteios diretamente da API da Caixa, sem precisar acessar o site manualmente. É uma ferramenta prática de averiguação de resultados, centralizando o gerenciamento das apostas e a verificação de premiações em um único lugar.

### ✨ Funcionalidades

- 📥 **Cadastro de jogos** — registre seus números apostados
- 🔍 **Averiguação de resultados** — confira automaticamente se seu jogo foi premiado no último sorteio
- 🔔 **Notificações** — seja alertado sobre novos resultados
- 🔐 **Autenticação** — login e cadastro de usuário com Firebase Auth
- ☁️ **Sincronização na nuvem** — seus jogos ficam salvos no Firestore

---

## 🚀 Tecnologias Utilizadas

| Tecnologia | Descrição |
|---|---|
| ![Kotlin](https://img.shields.io/badge/Kotlin-7F52FF?style=flat&logo=kotlin&logoColor=white) **Kotlin** | Linguagem principal de desenvolvimento |
| ![Jetpack Compose](https://img.shields.io/badge/Jetpack%20Compose-4285F4?style=flat&logo=jetpackcompose&logoColor=white) **Jetpack Compose** | UI declarativa nativa para Android |
| ![Android](https://img.shields.io/badge/Android-3DDC84?style=flat&logo=android&logoColor=white) **Android SDK** | SDK Android (min API 24 / target API 36) |
| ![Firebase](https://img.shields.io/badge/Firebase-FFCA28?style=flat&logo=firebase&logoColor=black) **Firebase Auth** | Autenticação de usuários |
| ![Firebase](https://img.shields.io/badge/Firestore-FFCA28?style=flat&logo=firebase&logoColor=black) **Firebase Firestore** | Banco de dados em nuvem |
| **Retrofit 2** | Consumo da API de resultados da Caixa |
| **Gson Converter** | Serialização/desserialização de JSON |
| **Navigation Compose** | Navegação entre telas |
| **Material Icons Extended** | Ícones do Material Design |
| **WorkManager** | Agendamento de tarefas em background (verificação de resultados) |
| **Material 3** | Design System do aplicativo |

---

## 🏗️ Arquitetura

O projeto segue os princípios de arquitetura recomendados pelo Google para aplicativos Android modernos, utilizando:

- **Jetpack Compose** para a camada de UI
- **ViewModel + StateFlow** para gerenciamento de estado
- **Retrofit** para comunicação com a API da Caixa
- **Firebase Firestore** para persistência dos dados dos jogos
- **WorkManager** para verificação periódica de novos resultados em background

---

## 📦 Como Executar

### Pré-requisitos

- Android Studio Hedgehog ou superior
- JDK 11+
- Conta no Firebase com projeto configurado

### Passos

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/loto-easy.git
   ```

2. Abra o projeto no **Android Studio**

3. Adicione o arquivo `google-services.json` na pasta `app/` (obtido no console do Firebase)

4. Sincronize as dependências com o Gradle

5. Execute o app em um emulador ou dispositivo físico (Android 7.0+)

---

## 🏫 Informações Acadêmicas

| Campo | Informação |
|---|---|
| **Instituição** | IFPE — Instituto Federal de Pernambuco |
| **Disciplina** | Desenvolvimento Mobile |
| **Plataforma** | Android |

---

## 📄 Licença

Este projeto foi desenvolvido para fins acadêmicos.
