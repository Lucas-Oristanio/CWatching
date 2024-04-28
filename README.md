# Repositório dedicado ao JAR individual do projeto em grupo CenterWatch da 2º Sprint - 2ADSA 

### CWatching by Lucas: Além da captura e tratamento dos dados de hardware, envio ao banco e exibição de alertas, estou utilizando o chatgpt da OpenIA para verificar os processos que estão em execução na máquina e através de uma pergunta, o chat irá responder se aquele processo é indevido ou não, no contexto de operação de call center. Através da resposta que obtermos, aquele processo pode ou não ser finalizado através do comando taskkill do windows. 
### Para minimizar as requisições ao chat, utilizei uma whitelist que contém diversos processos nativos da máquina, e assim enviar a requisição ao chat somente se for um processo que não estiver na whitelist.

#### Para usufruir desse projeto, certifique-se de obter uma api key disponibilizada em: https://platform.openai.com/api-keys. Configure a api key na classe "Gpt" do projeto em: CWatching\src\main\java\com\cw\gpt\Gpt.java 
### private static String KEY = "SUA-KEY"; 
#### É importante destacar que ao utilizar uma api key gratuita, você terá um limite muito baixo de requisições para enviar ao chat.
