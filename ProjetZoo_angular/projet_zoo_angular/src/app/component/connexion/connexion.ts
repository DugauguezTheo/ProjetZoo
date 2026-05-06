import { Component, inject, OnInit } from '@angular/core';
import { AuthService } from '../../service/auth-service';
import { Router } from '@angular/router';
import { FormBuilder, FormControl, FormGroup, Validators } from '@angular/forms';
import { AuthRequest } from '../../dto/auth-request';

@Component({
  selector: 'app-connexion',
  imports: [],
  templateUrl: './connexion.html',
  styleUrl: './connexion.css',
})
export class Connexion implements OnInit{

  private authService: AuthService = inject(AuthService);
  private router: Router = inject(Router);
  private formBuilder: FormBuilder = inject(FormBuilder);

  protected formAuth!: FormGroup;
  protected formUsernameCtrl!: FormControl;
  protected formPasswordCtrl!: FormControl;

  ngOnInit(): void {

    this.formUsernameCtrl = this.formBuilder.control("", [ Validators.required, Validators.email ]);
    this.formPasswordCtrl = this.formBuilder.control("", Validators.required);

    this.formAuth = this.formBuilder.group({
      username: this.formUsernameCtrl,
      password: this.formPasswordCtrl
    });
  }

  public connexion() {
    const authRequest: AuthRequest = {
      username: this.formUsernameCtrl.value,
      password: this.formPasswordCtrl.value
    };

    this.authService.auth(authRequest).subscribe(resp => {
      if (resp.success) {
        this.authService.token = resp.token;
        this.router.navigate([ '/animal' ]);
      }
    });
  }
}
