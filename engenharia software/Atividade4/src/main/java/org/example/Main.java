package org.example;

import io.github.ollama4j.Ollama;
import io.github.ollama4j.models.generate.OllamaGenerateRequest;
import io.github.ollama4j.models.response.OllamaResult;

public class Main {
    public static void main(String[] args) throws Exception {

        String model = "gemma3:270m";

        // We're just using our quick-setup utility here to instantiate Ollama. Use the following
        // to set it up with your Ollama configuration.
        Ollama ollama = new Ollama("http://127.0.0.1:11434/");
        ollama.setRequestTimeoutSeconds(200);

        StringBuilder promptBuilder = new StringBuilder();
        promptBuilder.append("You are an expert coder and understand different programming languages.\n");
        promptBuilder.append("Given a question, answer ONLY with code.\n");
        promptBuilder.append("Produce clean, formatted and indented code in markdown format.\n");
        promptBuilder.append("DO NOT include ANY extra text apart from code. Follow this instruction very strictly!\n");
        promptBuilder.append("If there's any additional information you want to add, use comments within code.\n");
        promptBuilder.append("Answer only in the programming language that has been asked for.\n");
        promptBuilder.append("How can I calculate the area of a circle in Python?\n");
        //promptBuilder.append("\n"); usar caso queira adicionar mais linhas de prompt

        String prompt = promptBuilder.toString();

        boolean raw = false;
        OllamaResult response =
                ollama.generate(
                        OllamaGenerateRequest.builder()
                                .withModel(model)
                                .withPrompt(prompt)
                                .withRaw(raw)
                                .build(),
                        null);
        System.out.println(response.getResponse());
    }
}