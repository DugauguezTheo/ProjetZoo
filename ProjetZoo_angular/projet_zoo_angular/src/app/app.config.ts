import { ApplicationConfig, provideBrowserGlobalErrorListeners } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideHttpClient, withInterceptors } from '@angular/common/http';
import { apiUrlInterceptor } from './interceptor/api-url-interceptor';
import { jwtHeaderInterceptor } from './interceptor/jwt-header-interceptor';
import { LogService } from './service/log-service';
import { APP_INITIALIZER } from '@angular/core';

function initConsoleCapture(logService: LogService) {
  const originalLog = console.log.bind(console);

  console.log = (...args: any[]) => {
    originalLog(...args);
    logService.log(args.join(' '));
  };
}

export const appConfig: ApplicationConfig = {
  providers: [
    provideBrowserGlobalErrorListeners(),
    provideRouter(routes),

    {
      provide: APP_INITIALIZER,
      useFactory: (logService: LogService) => {
        return () => initConsoleCapture(logService);
      },
      deps: [LogService],
      multi: true
    },

    provideHttpClient(
      // On configure le client HTTP et un ou plusieurs intercepteurs de requête HTTP
      withInterceptors([ apiUrlInterceptor, jwtHeaderInterceptor ])
    )
  ]
};
