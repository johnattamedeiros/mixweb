import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { MixwebTestModule } from '../../../test.module';
import { ExampleUpdateComponent } from 'app/entities/example/example-update.component';
import { ExampleService } from 'app/entities/example/example.service';
import { Example } from 'app/shared/model/example.model';

describe('Component Tests', () => {
  describe('Example Management Update Component', () => {
    let comp: ExampleUpdateComponent;
    let fixture: ComponentFixture<ExampleUpdateComponent>;
    let service: ExampleService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MixwebTestModule],
        declarations: [ExampleUpdateComponent],
        providers: [FormBuilder]
      })
        .overrideTemplate(ExampleUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(ExampleUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(ExampleService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new Example(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new Example();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
