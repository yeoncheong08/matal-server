package matal.store.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import matal.store.dto.StoreRequestDto;
import matal.store.dto.StoreResponseDto;
import matal.store.service.StoreService;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
@Tag(name = "store", description = "store API")
public class StoreController {

    private final StoreService storeService;

    //이름으로 가게 리스트 조회
    @GetMapping("/search/name")
    @Operation(summary = "Search store List Bu name", description = "검색 결과로 나온 가게 리스트들을 확인할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                content = {@Content(mediaType ="application/json",
                        array = @ArraySchema(schema = @Schema(implementation = StoreResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패"),
    })
    public List<StoreResponseDto> getStoreListByNmae(@RequestParam(name = "name", required = false) String name) {
        StoreRequestDto storeRequestDto = new StoreRequestDto(name, null, null);
        List<StoreResponseDto> storeResponseDtoList = new ArrayList<>();

        if (storeRequestDto.name() != null) {
            Optional<List<StoreResponseDto>> nameSearchResults = storeService.StoreNameSearch(storeRequestDto);
            nameSearchResults.ifPresent(storeResponseDtoList::addAll);
        }
        return storeResponseDtoList;
    }

    //카테고리로 가게 리스트 조회
    @GetMapping("/search/category")
    @Operation(summary = "Search store List Bu name", description = "검색 결과로 나온 가게 리스트들을 확인할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType ="application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StoreResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패"),
    })
    public List<StoreResponseDto> getStoreListByCategory(@RequestParam(name = "category", required = false) String category) {
        StoreRequestDto storeRequestDto = new StoreRequestDto(null, category, null);
        List<StoreResponseDto> storeResponseDtoList = new ArrayList<>();

        if (storeRequestDto.category() != null) {
            Optional<List<StoreResponseDto>> categorySearchResults = storeService.StoreCategorySearch(storeRequestDto);
            categorySearchResults.ifPresent(storeResponseDtoList::addAll);
        }
        return storeResponseDtoList;
    }

    //지하철역으로 가게 리스트 조회
    @GetMapping("/search/nearby_station")
    @Operation(summary = "Search store List Bu name", description = "검색 결과로 나온 가게 리스트들을 확인할 수 있다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공",
                    content = {@Content(mediaType ="application/json",
                            array = @ArraySchema(schema = @Schema(implementation = StoreResponseDto.class)))}),
            @ApiResponse(responseCode = "404", description = "실패"),
    })
    public List<StoreResponseDto> getStoreListByStation( @RequestParam(name = "nearby_station", required = false) String nearby_station) {
        StoreRequestDto storeRequestDto = new StoreRequestDto(null, null, nearby_station);
        List<StoreResponseDto> storeResponseDtoList = new ArrayList<>();

        if (storeRequestDto.nearby_station() != null) {
            Optional<List<StoreResponseDto>> stationSearchResults = storeService.StoreStationSearch(storeRequestDto);
            stationSearchResults.ifPresent(storeResponseDtoList::addAll);
        }
        return storeResponseDtoList;
    }

//    //가게 상세 정보 조회
//    @GetMapping("{storeid}")
//    @Operation(summary = "Get store detail infotmation", description = "가게 리스트 중 하나를 클릭하면 해당 가게의 상세 정보, 리뷰 등을 조회할 수 있다.")
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "200", description = "성공",
//            content = {@Content(schema = @Schema(implementation = StoreResponseDto.class))}),
//            @ApiResponse(responseCode = "404", description = "실패"),
//    })
//    public Optional<Store> getstoreDetail(
//            @PathVariable Long storeid
//    ) {
//        return storeRepository.findById(storeid);
//    }
}
