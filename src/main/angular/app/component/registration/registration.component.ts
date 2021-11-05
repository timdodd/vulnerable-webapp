import {Component} from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {RegistrationService} from "../../service/registration.service";
import {Router} from "@angular/router";
import {HttpStatusCode} from "@angular/common/http";

@Component({
  selector: 'app-registration',
  templateUrl: './registration.component.html',
  styleUrls: ['./registration.component.scss']
})
export class RegistrationComponent {

  formGroup = this.createFormGroup();
  errors: any = {};

  constructor(private formBuilder: FormBuilder,
              private registrationService: RegistrationService,
              private router: Router) {
  }

  register(): void {
    const user = this.formGroup.value;
    this.formGroup.disable({emitEvent: false});
    this.registrationService.register(user).subscribe(
      () => this.redirectToLogin(),
      httpError => {
        this.formGroup.enable({emitEvent: false});
        if (httpError.status === HttpStatusCode.BadRequest) {
          this.errors = httpError.error;
        }
      });
  }

  private redirectToLogin(): void {
    this.router.navigateByUrl("/login");
  }

  private createFormGroup(): FormGroup {
    return this.formBuilder.group({
      "firstName": null,
      "lastName": null,
      "username": null,
      "password": null
    });
  }

}
