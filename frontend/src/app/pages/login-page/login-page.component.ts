import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';

@Component({
  selector: 'app-login',
  templateUrl: './login-page.component.html',
  styleUrls: ['./login-page.component.css']
})
export class LoginPageComponent {
  loginForm: FormGroup;
  loginError: string | null = null;

  constructor(private router: Router, private fb: FormBuilder) {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      senha: ['', Validators.required]
    });
  }

  onSubmit() {
    const email = this.loginForm.get('email')?.value;
    const senha = this.loginForm.get('senha')?.value;

    if (email === 'user@teste.com' && senha === '123456') {
      alert('Login realizado com sucesso!');
      this.loginError = null;
      this.loginForm.reset();
      
      this.router.navigate(['/home']);
    } else {
      this.loginError = 'Email ou senha inválidos!';
    }
  }
}
