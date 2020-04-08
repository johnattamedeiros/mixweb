import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IExample } from 'app/shared/model/example.model';
import { ExampleService } from './example.service';

@Component({
  templateUrl: './example-delete-dialog.component.html'
})
export class ExampleDeleteDialogComponent {
  example?: IExample;

  constructor(protected exampleService: ExampleService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.exampleService.delete(id).subscribe(() => {
      this.eventManager.broadcast('exampleListModification');
      this.activeModal.close();
    });
  }
}
