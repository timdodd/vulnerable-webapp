import {Injectable} from '@angular/core';
import {Observable} from "rxjs";
import {HttpClient} from "@angular/common/http";
import {Joke} from "../model/joke.model";

const BASE_URI = "./api/jokes";

@Injectable({
  providedIn: 'root'
})
export class JokeService {

  constructor(private http: HttpClient) {
  }

  save(joke: Joke): Observable<Joke> {
    if (joke.jokeId) {
      return this.http.put<Joke>(`${BASE_URI}/${joke.jokeId}`, joke);
    }
    return this.http.post<Joke>(BASE_URI, joke);
  }

  delete(jokeId: string): Observable<void> {
    return this.http.delete<void>(`${BASE_URI}/${jokeId}`);
  }

  get(jokeId: string): Observable<Joke> {
    return this.http.get<Joke>(`${BASE_URI}/${jokeId}`);
  }

  findAll(): Observable<Joke[]> {
    return this.http.get<Joke[]>(BASE_URI);
  }
}
