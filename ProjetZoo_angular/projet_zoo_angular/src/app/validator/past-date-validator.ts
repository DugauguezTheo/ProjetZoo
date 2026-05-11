import { AbstractControl, ValidatorFn } from '@angular/forms';

export function pastDateValidator(): ValidatorFn {
  return (control: AbstractControl): { [key: string]: any } | null => {
    if (!control.value) return null;

    const inputDate = new Date(control.value);
    const today = new Date();
    // Réinitialise l'heure pour comparer uniquement les dates
    today.setHours(0, 0, 0, 0);
    inputDate.setHours(0, 0, 0, 0);

    if (inputDate >= today) {
      return { 'pastDate': true };
    }
    return null;
  };
}
