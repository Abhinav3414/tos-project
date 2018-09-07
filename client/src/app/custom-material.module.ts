import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatButtonModule } from '@angular/material';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatInputModule } from '@angular/material/input';
import { MatMenuModule } from '@angular/material/menu';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatDialogModule } from '@angular/material';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatAutocompleteModule } from '@angular/material/autocomplete';
import { MatSelectModule } from '@angular/material/select';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatNativeDateModule } from '@angular/material';
import { MatTabsModule } from '@angular/material/tabs';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatStepperModule } from '@angular/material/stepper';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';
import {MatPaginatorModule} from '@angular/material/paginator';
import {MatSortModule} from '@angular/material/sort';
import {MatTableModule} from '@angular/material/table';

@NgModule({
  imports: [MatButtonModule, MatToolbarModule, MatCardModule, MatInputModule, MatIconModule, MatCheckboxModule,
    MatMenuModule, MatFormFieldModule, MatDialogModule, MatExpansionModule, MatAutocompleteModule, MatSelectModule,
    MatDatepickerModule, MatNativeDateModule, MatTabsModule, MatStepperModule, MatProgressSpinnerModule, MatPaginatorModule,
    MatSortModule, MatTableModule],
  exports: [MatButtonModule, MatToolbarModule, MatCardModule, MatInputModule, MatIconModule, MatCheckboxModule,
    MatMenuModule, MatFormFieldModule, MatDialogModule, MatExpansionModule, MatAutocompleteModule, MatSelectModule,
    MatDatepickerModule, MatNativeDateModule, MatTabsModule, MatStepperModule, MatProgressSpinnerModule, MatPaginatorModule,
    MatSortModule, MatTableModule]
})
export class CustomMaterialModule { }
