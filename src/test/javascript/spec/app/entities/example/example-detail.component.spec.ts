import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { MixwebTestModule } from '../../../test.module';
import { ExampleDetailComponent } from 'app/entities/example/example-detail.component';
import { Example } from 'app/shared/model/example.model';

describe('Component Tests', () => {
  describe('Example Management Detail Component', () => {
    let comp: ExampleDetailComponent;
    let fixture: ComponentFixture<ExampleDetailComponent>;
    const route = ({ data: of({ example: new Example(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [MixwebTestModule],
        declarations: [ExampleDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }]
      })
        .overrideTemplate(ExampleDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(ExampleDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load example on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.example).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
