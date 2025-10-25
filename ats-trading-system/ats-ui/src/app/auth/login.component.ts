// auth/login.component.ts (standalone)
@Component({ standalone:true, selector:'app-login',
  imports:[CommonModule, ReactiveFormsModule, MatCardModule, MatFormFieldModule, MatInputModule, MatButtonModule],
  template:`
  <div class="h-screen grid place-items-center bg-gray-50">
    <mat-card class="w-full max-w-md p-6">
      <h2 class="text-2xl font-semibold mb-4">ATS Login</h2>
      <form [formGroup]="fg" (ngSubmit)="submit()">
        <mat-form-field class="w-full"><mat-label>Username</mat-label>
          <input matInput formControlName="username"></mat-input></mat-form-field>
        <mat-form-field class="w-full"><mat-label>Password</mat-label>
          <input matInput type="password" formControlName="password"></mat-input></mat-form-field>
        <button mat-raised-button color="primary" class="w-full">Sign in</button>
      </form>
    </mat-card>
  </div>`})
export class LoginComponent {
  fg = new FormGroup({ username: new FormControl('trader', {nonNullable:true}), password: new FormControl('password',{nonNullable:true})});
  submit(){ localStorage.setItem('token','dev'); location.href='/trades'; } // Phase 1: real JWT
}
