import { CommonModule } from '@angular/common';
import { Component, Input } from '@angular/core';
import {
  FormBuilder,
  FormGroup,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';

@Component({
  selector: 'app-sidebar',
  standalone: true,
  imports: [ReactiveFormsModule, CommonModule],
  templateUrl: './sidebar.component.html',
  styleUrl: './sidebar.component.css',
})
export class SidebarComponent {
  addPeople(people: number) {
    if (people === -1 && this.form.get('people')?.value === 1) {
      this.form.get('people')?.setValue(1);
    } else {
      this.form
        .get('people')
        ?.setValue(this.form.get('people')?.value + people);
    }
  }
  onSubmit() {}
  form: FormGroup<any>;
  @Input() id: number;

  constructor(private fb: FormBuilder) {
    this.form = this.fb.group({
      people: [1, [Validators.required, Validators.min(1)]],
      client: ['', [Validators.required]],
    });
  }
}
