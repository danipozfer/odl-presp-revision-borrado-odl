package com.santalucia.cdc.core.readers;

import java.util.Iterator;


import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.data.AbstractPaginatedDataItemReader;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.util.ClassUtils;

import com.santalucia.arq.ams.odl.recibos.api.model.ReciboDetailResource;


@SuppressWarnings("NullAway")
public class RecibosOdlItemReader
  extends AbstractPaginatedDataItemReader<ReciboDetailResource>
  implements StepExecutionListener {

  private static final int PAGE_SIZE = 500;

  private final RecibosOdlService recibosOdlService;


  private StepExecution stepExecution;

  /**
   * constructor
   *
   * @param recibosOdlService
   * @param inputParameters
   */
  public RecibosOdlItemReader(
    RecibosOdlService recibosOdlService) {
    super();
    setName(ClassUtils.getShortName(RecibosOdlItemReader.class));
    this.recibosOdlService = recibosOdlService;
  }

  /**
   * beforeStep
   *
   * @param stepExecution
   */
  @Override
  public void beforeStep(StepExecution stepExecution) {
    this.stepExecution = stepExecution;
  }

  /**
   * afterStep
   *
   * @param stepExecution
   * @return
   */
  @Override
  public ExitStatus afterStep(StepExecution stepExecution) {
    return this.stepExecution.getExitStatus();
  }

  /**
   * doPageRead
   *
   * @return
   */
  @Override
  protected Iterator<ReciboDetailResource> doPageRead() {
    Pageable pageable = PageRequest.of(page, PAGE_SIZE, Sort.Direction.ASC, "cabecera.datosIdentificativos.idReciboODL");
    return recibosOdlService.getRecibosResources( pageable);
  }
}
