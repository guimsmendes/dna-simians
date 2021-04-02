package br.com.guimsmendes.dnasimians.unit.entrypoint.controller;

import br.com.guimsmendes.dnasimians.entrypoint.controller.SimianController;
import br.com.guimsmendes.dnasimians.entrypoint.mapper.StatsMapper;
import br.com.guimsmendes.dnasimians.entrypoint.model.request.SimianRequest;
import br.com.guimsmendes.dnasimians.entrypoint.model.response.StatsResponse;
import br.com.guimsmendes.dnasimians.usecase.DnaUseCase;
import br.com.guimsmendes.dnasimians.usecase.domain.DnaDomain;
import br.com.guimsmendes.dnasimians.usecase.exception.UseCaseException;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SimianControllerTest {

	@InjectMocks
	private SimianController simianController;

	@Mock
	private DnaUseCase dnaUseCase;

	@Mock
	private StatsMapper statsMapper;

	@Test
	void isSimianOk() {
		when(dnaUseCase.isSimian(any(DnaDomain.class))).thenReturn(true);
		ResponseEntity<Object> result = simianController.isSimian(mockValidSimianRequest());
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.OK).build());
	}

	@Test
	void isSimianForbidden() {
		when(dnaUseCase.isSimian(any(DnaDomain.class))).thenReturn(false);
		ResponseEntity<?> result = simianController.isSimian(mockValidSimianRequest());
		assertThat(result).isEqualTo(ResponseEntity.status(HttpStatus.FORBIDDEN).build());
	}

	@Test
	void validRequestBody() {
		SimianRequest simianRequest = mockValidSimianRequest();
		DnaDomain dnaDomain = simianRequest.asDnaDomain();
		assertEquals(dnaDomain.getDnaSequence(), mockDnaSequence());
	}

	@Test
	void invalidRequestBody() {
		SimianRequest simianRequest = mockInvalidSimianRequest();
		Assertions.assertThrows(IllegalArgumentException.class, simianRequest::asDnaDomain);
	}

	@Test
	void inputOutOfBoundsRequestBody() {
		SimianRequest simianRequest = mockInputOutOfBoundsSimianRequest();
		Assertions.assertThrows(UseCaseException.InputOutOfBounds.class, simianRequest::asDnaDomain);
	}

	@Test
	void getStats() {
		when(dnaUseCase.getStats()).thenReturn(mockDnaDomain());
		when(statsMapper.toStatsResponse(any(DnaDomain.class))).thenReturn(mockStatsResponse());
		ResponseEntity<StatsResponse> result = simianController.getStats();
		assertEquals(result.getStatusCodeValue(), ResponseEntity.status(HttpStatus.OK).build().getStatusCodeValue());
		assertEquals(40, Objects.requireNonNull(result.getBody()).getCountMutantDna());
		assertEquals(100, Objects.requireNonNull(result.getBody()).getCountHumanDna());
		assertEquals(BigDecimal.valueOf(0.4), Objects.requireNonNull(result.getBody()).getRatio());
	}

	private SimianRequest mockValidSimianRequest() {
		String[] dnaSequence = { "ATC", "CGT", "AGG"};
		return new SimianRequest(dnaSequence);
	}

	private SimianRequest mockInvalidSimianRequest() {
		String[] dnaSequence = { "JJAA", "CGRT", "GMHU", "HUMN" };
		return new SimianRequest(dnaSequence);
	}

	private SimianRequest mockInputOutOfBoundsSimianRequest() {
		String[] dnaSequence = { "AAAAaa", "CGTA", "GGGU", "AATC" };
		return new SimianRequest(dnaSequence);
	}

	private DnaDomain mockDnaDomain() {
		DnaDomain dnaDomain = new DnaDomain();
		dnaDomain.setCountMutantDna(40);
		dnaDomain.setCountHumanDna(100);
		dnaDomain.setRatio(BigDecimal.valueOf(0.4));
		return dnaDomain;
	}

	private StatsResponse mockStatsResponse() {
		return StatsResponse.builder().countHumanDna(100).countMutantDna(40).ratio(BigDecimal.valueOf(0.4)).build();
	}

	private List<List<Character>> mockDnaSequence() {
		List<Character> dnaList1 = new ArrayList<>();
		dnaList1.add('A');
		dnaList1.add('T');
		dnaList1.add('C');
		List<Character> dnaList2 = new ArrayList<>();
		dnaList2.add('C');
		dnaList2.add('G');
		dnaList2.add('T');
		List<Character> dnaList3 = new ArrayList<>();
		dnaList3.add('A');
		dnaList3.add('G');
		dnaList3.add('G');
		List<List<Character>> dnaSequence = new ArrayList<>();
		dnaSequence.add(dnaList1);
		dnaSequence.add(dnaList2);
		dnaSequence.add(dnaList3);
		return dnaSequence;
	}
}