import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function matchPasswords(controlName: string, matchingControlName: string): ValidatorFn {
  return (formGroup: AbstractControl): ValidationErrors | null => {
    const control = formGroup.get(controlName);
    const matchingControl = formGroup.get(matchingControlName);

    if (!control || !matchingControl) {
      return null; // Form controls not found
    }

    // If values do not match, set the error on the matching control
    if (control.value !== matchingControl.value) {
      matchingControl.setErrors({ passwordMismatch: true });
    } else {
      matchingControl.setErrors(null); // Clear the error if values match
    }

    return null; // No errors on the group itself
  };
}
