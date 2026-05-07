import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth-service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthRequest } from '../../dto/auth-request';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-connexion',
  imports: [
    CommonModule,
    ReactiveFormsModule
  ],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css',
})
export class Connexion implements OnInit{

  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formAuth!: FormGroup;
  protected formLoginCtrl!: FormControl;
  protected formPasswordCtrl!: FormControl;

  ngOnInit(): void {

    this.formLoginCtrl = this.formBuilder.control("", [ Validators.required, Validators.email ]);
    this.formPasswordCtrl = this.formBuilder.control("", Validators.required);

    this.formAuth = this.formBuilder.group({
      login: this.formLoginCtrl,
      password: this.formPasswordCtrl
    });
  }

  public connexion() {
    const authRequest: AuthRequest = {
      login: this.formLoginCtrl.value,
      password: this.formPasswordCtrl.value
    };

    this.authService.auth(authRequest).subscribe(resp => {
      if (resp.success) {
        this.authService.token = resp.token;
        this.authService.role = resp.role;
        this.authService.login = resp.login;

        if (this.authService.isAdmin()){
          this.router.navigate([ '/admin' ]);
        }
        else if (this.authService.isVeterinaire()) {
          this.router.navigate([ '/veterinaire ']);
        }
        else {
          this.router.navigate([ '/visiteur' ]);
        }
      }
    });
  }
}
