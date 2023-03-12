package com.tvd12.example.generics;

import java.util.UUID;

public abstract class VersionedLinkServiceBase<
    TEntity extends AbstractVersionedLinkEntity<TParent, TParentKey, TChild, TChildKey>,
    TParent extends IEntityBase<TParentKey>,
    TParentKey,
    TChild extends IEntityBase<TChildKey>,
    TChildKey,
    TDtoCreate extends IVersionedLinkCreate<TParentKey, TChildKey, UUID>,
    TDtoUpdate extends IDtoBase<Long>,
    TDtoOutput extends IDtoBase<Long>,
    TDtoListItem extends IDtoBase<Long>,
    TMapper extends IEntityMapperEx<TEntity, TDtoCreate, TDtoUpdate, TDtoOutput, TDtoListItem>,
    TRevision extends AbstractVersionedFileEntity<TChild>,
    TDtoRevisionCreate extends VersionedEntityCreate,
    TDtoRevisionOutput extends VersionedEntityOutput,
    TRevisionService extends VersionedFileEntityServiceBase<?, ?, TDtoRevisionCreate, ?, TDtoRevisionOutput, ?, ?>
    > extends GatewayCrudServiceBase<
    TEntity,
    Long,
    TDtoCreate,
    TDtoUpdate,
    TDtoOutput,
    TDtoListItem,
    TMapper> {}
