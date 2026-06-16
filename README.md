# 🎵 Diário Musical

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos (POO), com o objetivo de registrar uma música por dia e gerar estatísticas semanais e mensais sobre os hábitos musicais do usuário.

## 📋 Funcionalidades

- Cadastro de uma música por dia
- Armazenamento dos registros em arquivo CSV
- Listagem completa do histórico musical
- Ranking semanal
- Ranking mensal
- Identificação de músicas repetidas
- Exibição do artista mais ouvido
- Exibição do gênero musical mais frequente
- Tratamento de exceções personalizadas

---

## 🏗️ Estrutura do Projeto

```
.
├── Principal.java
├── Diario.java
├── Musica.java
├── MusicaDaSemana.java
├── Registravel.java
├── MusicaJaRegistradaException.java
├── musicas.csv
└── README.md
```

---

## 📚 Conceitos de POO Aplicados

### Herança
A classe `MusicaDaSemana` herda da classe abstrata `Musica`, reutilizando atributos e comportamentos comuns.

```java
public class MusicaDaSemana extends Musica
```

### Abstração
A classe `Musica` é abstrata e define a estrutura básica de uma música.

```java
public abstract class Musica
```

### Polimorfismo
As coleções utilizam o tipo `Musica`, permitindo armazenar objetos da subclasse `MusicaDaSemana`.

```java
List<Musica> lista = lerCSV();
```

### Interface
A interface `Registravel` define um contrato para classes que podem ser registradas.

```java
public interface Registravel
```

### Encapsulamento
Os atributos são protegidos e acessados por meio de getters e setters.

### Tratamento de Exceções
Foi criada a exceção personalizada:

```java
MusicaJaRegistradaException
```

Ela impede que mais de uma música seja cadastrada na mesma data.

---

## 💾 Persistência de Dados

Os registros são armazenados no arquivo:

```text
musicas.csv
```

Formato:

```csv
Nome da Faixa,Artista,Álbum,Gênero,Ano de Lançamento,Data
```

Exemplo:

```csv
Yellow,Coldplay,Parachutes,Rock,2000,2025-06-10
```

---

## 🚀 Como Executar

### 1. Compilar

```bash
javac *.java
```

### 2. Executar

```bash
java Principal
```

---

## 📖 Menu do Sistema

```text
=== DIARIO MUSICAL ===

1. Cadastrar música do dia
2. Listar músicas
3. Ranking semanal
4. Ranking mensal
0. Sair
```

---

## 📊 Ranking Semanal

O sistema apresenta:

- Músicas registradas nos últimos 7 dias
- Faixas repetidas
- Artista mais presente
- Gênero mais presente

---

## 📈 Ranking Mensal

O sistema apresenta:

- Músicas registradas no mês atual
- Faixas repetidas
- Artista mais ouvido do mês
- Gênero predominante

---

## ⚠️ Regras de Negócio

- Apenas uma música pode ser cadastrada por dia.
- O sistema valida automaticamente a data atual.
- Caso já exista um registro para o dia, uma exceção é lançada.

---

## 👨‍💻 Tecnologias Utilizadas

- Java
- Programação Orientada a Objetos (POO)
- Manipulação de Arquivos CSV
- Collections Framework
- Tratamento de Exceções

---

## 🎯 Objetivo Acadêmico

Este projeto foi desenvolvido para demonstrar a aplicação prática dos principais conceitos de Programação Orientada a Objetos:

- Classes e Objetos
- Herança
- Polimorfismo
- Encapsulamento
- Abstração
- Interfaces
- Exceções Personalizadas
- Persistência de Dados

---

## 👥 Autores

Projeto desenvolvido para a disciplina de Programação Orientada a Objetos.

**Alunos:** _Inserir nomes dos integrantes_

**Professor:** _Inserir nome do professor_

**Instituição:** _Inserir nome da instituição_