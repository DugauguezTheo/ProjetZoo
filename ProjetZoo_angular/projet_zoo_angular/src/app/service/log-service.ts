// log.service.ts
import { Injectable } from '@angular/core';
import { BehaviorSubject } from 'rxjs';

@Injectable({ providedIn: 'root' })
export class LogService {
  private logs: string[] = [];
  private logsSubject = new BehaviorSubject<string[]>([]);
  logs$ = this.logsSubject.asObservable();

  log(msg: string) {
    this.logs.push(msg);
    this.logsSubject.next([...this.logs]); // copie pour trigger UI
  }

  getAll() {
    return this.logs;
  }

  clear() {
    this.logs = [];
    this.logsSubject.next([]);
  }
}