package com.knishe;

import com.knishe.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class BookService {

    private final WebClient webClient;

    public Mono<Book> findBookById() {
        return webClient.get()
                .uri("https://www.anapioficeandfire.com/api/books/2")
                .exchangeToMono(this::handleResponse);
    }

    private Mono<Book> handleResponse(ClientResponse response) {
        if (response.statusCode().is2xxSuccessful()) {
            return response.bodyToMono(Book.class);
        } else if (response.statusCode().is4xxClientError()) {
            return Mono.error(new RuntimeException("Book not found"));
        } else if (response.statusCode().is5xxServerError()) {
            return Mono.error(new RuntimeException("Server error"));
        } else {
            return Mono.error(new RuntimeException("Unexpected error"));
        }
    }
}
