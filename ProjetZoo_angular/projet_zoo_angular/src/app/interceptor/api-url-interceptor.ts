import { HttpInterceptorFn } from '@angular/common/http';

export const apiUrlInterceptor: HttpInterceptorFn = (req, next) => {
  console.log("Interception de la requête -> ajout du préfixe http://localhost:8080/api");

  const apiRequest = req.clone({
    url: "http://localhost:8080/api" + req.url
  });

  return next(apiRequest);
};
